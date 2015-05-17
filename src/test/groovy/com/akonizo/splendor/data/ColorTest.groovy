package com.akonizo.splendor.data

import spock.lang.Specification

class ColorTest extends Specification {

    def "Check Colors enumeration"() {
        expect:
            Color.WHITE < Color.BLUE
            Color.BLUE < Color.GREEN
            Color.GREEN < Color.RED
            Color.RED < Color.BLACK
    
            Color.WHITE == 'WHITE' as Color
            Color.BLUE == 'BLUE' as Color
            Color.GREEN == 'GREEN' as Color
            Color.RED == 'RED' as Color
            Color.BLACK == 'BLACK' as Color
    
            Color.WHITE == Color.get( 'W' )
            Color.BLUE == Color.get( 'B' )
            Color.BLUE == Color.get( 'b' )
            Color.BLUE == Color.get( (char) 'B' )
            Color.BLUE == Color.get( (char) 'b' )
            Color.GREEN == Color.get( 'G' )
            Color.RED == Color.get( 'R' )
            Color.BLACK == Color.get( 'K' )
    }

}
