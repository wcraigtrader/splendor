package com.akonizo.splendor

import groovy.util.logging.Slf4j

import java.awt.Color
import java.awt.RenderingHints
import java.awt.Shape
import java.awt.geom.Ellipse2D
import java.awt.geom.GeneralPath
import java.awt.geom.Path2D

import org.jfree.graphics2d.svg.SVGGraphics2D

import com.akonizo.splendor.data.*

@Slf4j
class ColorWheel {
    static Cards cards
    static String output = "colorwheel.svg"
    static SVGGraphics2D g2d

    static int width = 900
    static int height = 900

    static double centerY
    static double centerX

    static double ringOffset
    static double ringWidth
    static double maximumRadius

    static double alignment
    static double wedgeAngle
    static double wedgeOffset

    static double gemRadius

    static final Color BLUE_DECK_BACKGROUND = new Color( 0xbbddff )
    static final Color GOLD_DECK_BACKGROUND = new Color( 0xffffcc )
    static final Color GREEN_DECK_BACKGROUND = new Color( 0xbbffdd )

    static main(args) {
        switch (args.length) {
            case 2:
                def sizes = args[1].split(  '[Xx]' )
                switch (sizes.length) {
                    case 2:
                        height = Integer.valueOf(  sizes[1] )
                        break
                    case 1:
                        height = Integer.valueOf(  sizes[0] )
                        width = Integer.valueOf(  sizes[0] )
                }
            case 1:
                output = args[0]
        }

        cards = Cards.getSplendor()

        g2d = new SVGGraphics2D(width, height)
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )
        g2d.setRenderingHint( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY )

        centerX = g2d.width / 2.0
        centerY = g2d.height / 2.0

        maximumRadius = Math.min( centerX, centerY )
        ringWidth = maximumRadius / 20.0
        ringOffset = ringWidth / 2.0 - 2

        alignment = - Math.PI / 2.0
        wedgeAngle = 2.0 * Math.PI / 5.0
        wedgeOffset = wedgeAngle / 2.0

        gemRadius = ringOffset / 2.0

        placeBackground()
        placeWedges()
        placeRings()
        placeCards()

        new File( output).withWriter { w -> w.write( g2d.SVGDocument ) }
    }

    static void drawLissajous() {
        GeneralPath path = new GeneralPath()
        double dt = Math.PI / 720
        int w = g2d.width / 2
        int h = g2d.height / 2
        path.reset()
        path.moveTo(w, h)
        for (double t = 0; t < 2 * Math.PI; t += dt) {
            double x = w * Math.sin(5 * t) + w
            double y = h * Math.sin(4 * t) + h
            path.lineTo(x, y)
        }
        g2d.setColor(Color.blue)
        g2d.draw(path)
    }

    static void placeBackground() { }

    static void placeWedges() {

        def inner = ringRadius( 0 )
        def outer = ringRadius( 19 )

        int position = 0
        for (Gem gem : Gem.ordering( Gem.WHITE ) ) {
            // draw wedge, light gray border, filled with gem.color
            def leftAngle  = alignment + wedgeAngle * ( position + 0.0 ) // + Math.PI / 180
            def rightAngle = alignment + wedgeAngle * ( position + 1.0 ) // - Math.PI / 180

            GeneralPath wedge = new GeneralPath( Path2D.WIND_EVEN_ODD )

            def leftInX = centerX + inner * Math.cos( leftAngle )
            def leftInY = centerY + inner * Math.sin( leftAngle )

            wedge.moveTo( leftInX, leftInY )
            addArc( wedge, outer, leftAngle, rightAngle, 720 )
            addArc( wedge, inner, rightAngle, leftAngle, 72 )
            wedge.closePath()

            g2d.setColor( new Color( gem.color ) )
            g2d.fill( wedge )
            g2d.setColor( new Color( 0x999999 ) )
            g2d.draw( wedge )

            position++
        }
    }

    static void placeRings() {

        def BACKGROUND = new Color( 0xffffff )
        def RING_BORDER = new Color( 0x999999 )

        for ( int ring=18; ring>0; ring-- ) {
            // draw ring, dark gray border, filled with deck.color, over wedge
            def radius = ringRadius( ring )
            def inner = radius-ringOffset
            def outer = radius+ringOffset
            def color = ring > 14 ? BLUE_DECK_BACKGROUND : ring > 8 ? GOLD_DECK_BACKGROUND : GREEN_DECK_BACKGROUND

            int position = 0
            for (Gem gem : Gem.ordering( Gem.WHITE ) ) {
                // draw wedge, light gray border, filled with gem.color
                def leftAngle  = alignment + wedgeAngle * ( position + 0.05 )
                def rightAngle = alignment + wedgeAngle * ( position + 0.95 )

                GeneralPath wedge = new GeneralPath( Path2D.WIND_EVEN_ODD )

                def leftInX = centerX + inner * Math.cos( leftAngle )
                def leftInY = centerY + inner * Math.sin( leftAngle )

                wedge.moveTo( leftInX, leftInY )
                addArc( wedge, outer, leftAngle, rightAngle, 720 )
                addArc( wedge, inner, rightAngle, leftAngle, 72 )
                wedge.closePath()

                g2d.setColor( color )
                g2d.fill( wedge )
                g2d.setColor( RING_BORDER )
                g2d.draw( wedge )

                position++
            }
        }
    }

    static void placeCards() {
        def ordering = Gem.ordering( Gem.WHITE )

        for( Card card : cards.cards ) {
            int axis = ordering.indexOf( card.provides )

            int pos = 0
            for ( Gem gem : Gem.ordering( card.provides.succ ) ) {
                for ( int g=0 ; g < card.counts[ gem ]; g++ ) {
                    drawGem( card.ring, axis, pos, card.cost, gem.color )
                    // draw circle, light gray border, filled with gem.color, inside ring, inside wedge
                    pos++
                }
            }
        }
    }

    static void addArc( Path2D shape, double radius, double start, double end, int slices ) {
        double delta = ( end - start ) / slices
        double angle = start
        for ( int i=0 ; i < slices; i++ ) {
            def point = radialPoint( radius, angle )
            shape.lineTo( point.x, point.y)
            angle += delta
        }
    }

    static def radialPoint( double radius, double angle ) {
        def x = centerX + radius * Math.cos( angle )
        def y = centerY + radius * Math.sin( angle )
        return [x: x, y: y]
    }
    
    static drawGem( int ring, int axis, int pos, int max, int color ) {
        def radius = ringRadius( ring )
        def baseAngle = wedgeAngle * axis + wedgeOffset + alignment
        def gemAngle = (pos+0.5-max/2.0) * Math.atan( 3 * gemRadius / radius )

        def gx = centerX + radius * Math.cos( baseAngle + gemAngle )
        def gy = centerY + radius * Math.sin( baseAngle + gemAngle )

        Shape gem = new Ellipse2D.Double(gx-gemRadius, gy-gemRadius, 2*gemRadius, 2*gemRadius )

        g2d.setColor( new Color( color ) )
        g2d.fill( gem )
        g2d.setColor( new Color( 0x333333 ) )
        g2d.draw( gem )

    }

    static double ringRadius( int ring ) {
        return ( ring + 1 ) * ringWidth
    }

}
