package com.akonizo.splendor.data

import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import com.xlson.groovycsv.CsvParser

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
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
        log.info( "Clearing stored cards" )
        cards.clear()

        for ( row in CsvParser.parseCsv(stream) ) {
            def card = new Card( row.deck, row.produces, row.requires, row.value )
            log.info( "Add ${card}" )
            cards.add( card )
        }
    }
}
