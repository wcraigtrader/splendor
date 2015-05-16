package com.akonizo.splendor.data

import static com.xlson.groovycsv.CsvParser.parseCsv
import groovy.util.logging.Slf4j

@Slf4j
class Cards {

    static final String WHITE = 'WHITE'
    static final String BLUE = 'BLUE'
    static final String GREEN = 'GREEN'
    static final String RED = 'RED'
    static final String BLACK = 'BLACK'
    static final String GOLD = 'GOLD'

    Set<Card> cards = new HashSet<Card>()

    Cards() {
        load(new InputStreamReader(getClass().classLoader.getResourceAsStream('cards.csv')))
    }

    void load(String data) {
        load( new StringReader( data ) )
    }
    
    void load(Reader stream) {
        def data = parseCsv(stream, autoDetect:true)
        for (card in data) {
            cards.add( new Card( card.deck, card.produces, card.requires, card.value ) )
        }
    }
}

class Card {

    Deck deck
    Color provides
    String requires
    int value

    Card( String d, String p, String r, String v='') {
        deck = Deck.values()[Integer.valueOf( d )-1]
        provides = p.toUpperCase() as Color
        requires = r
        value = v ? Integer.valueOf( v ) : 0
        assert 0 <= value && value <= 5
    }
}

enum Deck {
    GREEN, GOLD, BLUE
}

enum Color {
    WHITE( 'W' ),
    BLUE( 'B' ),
    GREEN( 'G' ),
    RED( 'R' ),
    BLACK( 'K' )

    final char abbrev

    static final Map map

    static {
        map = [:] as HashMap
        values().each { color ->
            map.put( (Character) color.abbrev, color )
        }
    }

    Color( String a ) {
        this.abbrev = a[0]
    }

    static get( String id ) {
        return get( (char) id[0] )
    }
    
    static get( Character id ) {
        return map[ id.toUpperCase() ]
    }
}