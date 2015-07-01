package com.akonizo.splendor.data

import static com.akonizo.splendor.data.Gem.WHITE
import static com.akonizo.splendor.data.Gem.BLUE
import static com.akonizo.splendor.data.Gem.GREEN
import static com.akonizo.splendor.data.Gem.RED
import static com.akonizo.splendor.data.Gem.BLACK

import spock.lang.Specification

class GemTest extends Specification {

    def "Check Gem enumeration"() {
        expect:
        WHITE < BLUE
        BLUE  < GREEN
        GREEN < RED
        RED   < BLACK
    }

    def "Check Gem conversion"() {
        expect:
        Gem.WHITE == 'WHITE' as Gem
        Gem.BLUE  == 'BLUE' as Gem
        Gem.GREEN == 'GREEN' as Gem
        Gem.RED   == 'RED' as Gem
        Gem.BLACK == 'BLACK' as Gem
    }

    def "Check Gem lookup"() {
        expect:
        WHITE == Gem.get( 'W' )
        BLUE  == Gem.get( 'B' )
        BLUE  == Gem.get( 'b' )
        BLUE  == Gem.get( (char) 'B' )
        BLUE  == Gem.get( (char) 'b' )
        GREEN == Gem.get( 'G' )
        RED   == Gem.get( 'R' )
        BLACK == Gem.get( 'K' )
    }


    def "Check Gem ordering"() {
        expect:
        Gem.ordering( WHITE ) == [WHITE, BLUE, GREEN, RED, BLACK,]
        Gem.ordering( BLUE  ) == [BLUE, GREEN, RED, BLACK, WHITE,]
        Gem.ordering( GREEN ) == [GREEN, RED, BLACK, WHITE, BLUE,]
        Gem.ordering( RED   ) == [RED, BLACK, WHITE, BLUE, GREEN,]
        Gem.ordering( BLACK ) == [BLACK, WHITE, BLUE, GREEN, RED,]
    }
}
