package com.akonizo.splendor.data

import spock.lang.Specification

class GemTest extends Specification {

    def "Check Gem enumeration"() {
        expect:
            Gem.WHITE < Gem.BLUE
            Gem.BLUE < Gem.GREEN
            Gem.GREEN < Gem.RED
            Gem.RED < Gem.BLACK
    
            Gem.WHITE == 'WHITE' as Gem
            Gem.BLUE == 'BLUE' as Gem
            Gem.GREEN == 'GREEN' as Gem
            Gem.RED == 'RED' as Gem
            Gem.BLACK == 'BLACK' as Gem
    
            Gem.WHITE == Gem.get( 'W' )
            Gem.BLUE == Gem.get( 'B' )
            Gem.BLUE == Gem.get( 'b' )
            Gem.BLUE == Gem.get( (char) 'B' )
            Gem.BLUE == Gem.get( (char) 'b' )
            Gem.GREEN == Gem.get( 'G' )
            Gem.RED == Gem.get( 'R' )
            Gem.BLACK == Gem.get( 'K' )
    }

}
