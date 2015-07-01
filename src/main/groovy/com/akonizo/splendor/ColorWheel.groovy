package com.akonizo.splendor

import groovy.util.logging.Slf4j

import java.awt.Color
import java.awt.RenderingHints
import java.awt.Shape
import java.awt.geom.Ellipse2D
import java.awt.geom.GeneralPath

import org.jfree.graphics2d.svg.SVGGraphics2D

import com.akonizo.splendor.data.*

@Slf4j
class ColorWheel {

    static Cards cards
    static String output = "colorwheel.svg"
    static SVGGraphics2D g2d

    static int width = 900
    static int height = 900

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

        // drawLissajous()
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
        double wedgeAngle = 2 * Math.PI / 5
        double wedgeOffset = -(2 * Math.PI / 10)

        int position = 0
        for (Gem gem : Gem.ordering( Gem.WHITE ) ) {
            // draw wedge, light gray border, filled with gem.color
            position++
        }
    }

    static void placeRings() {
        
        def BACKGROUND = new Color( 0xffffff )
        def RING_FILL = new Color( 0xcccccc )
        def RING_BORDER = new Color( 0x666666 )
        
        def cx = g2d.width / 2.0
        def cy = g2d.height / 2.0
        def mr = Math.min( cx, cy )
        def rr = mr / 20.0
        def offset = rr / 2.0

        for ( int ring=18; ring>0; ring-- ) {
            // draw ring, dark gray border, filled with deck.color, over wedge
            def radius = ( ring + 1 ) * rr
            def innerRadius = radius-offset+2
            def outerRadius = radius+offset-2
            
            log.info( "Circle: ${innerRadius} => ${outerRadius}" )
            Shape outer = new Ellipse2D.Double(cx-outerRadius, cy-outerRadius, 2*outerRadius, 2*outerRadius )
            Shape inner = new Ellipse2D.Double(cx-innerRadius, cy-innerRadius, 2*innerRadius, 2*innerRadius )
            
            if (ring > 14) {
                g2d.setColor( new Color( 0xbbddff ) )
            } else if (ring > 8) {
                g2d.setColor( new Color( 0xffffcc ) )
            } else {
                g2d.setColor(  new Color( 0xbbffdd ) )
            }
            g2d.fill( outer )
            g2d.setColor( BACKGROUND )
            g2d.fill( inner )
            g2d.setColor( RING_BORDER )
            g2d.draw( outer )
            g2d.draw( inner )
        }
    }

    static void placeCards() {
        for( Card card : cards.cards ) {
            int pos = 0
            for ( Gem gem : Gem.ordering( card.provides ) ) {
                for ( int g : card.counts[ gem ] ) {
                    // draw circle, light gray border, filled with gem.color, inside ring, inside wedge
                    pos++
                }
            }
        }
    }
}
