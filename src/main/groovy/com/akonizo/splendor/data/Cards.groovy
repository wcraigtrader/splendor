package com.akonizo.splendor.data

import groovy.util.logging.Slf4j

import com.xlson.groovycsv.CsvParser

@Slf4j
class Cards {

    /** Set of loaded cards */
    Set<Card> cards = new HashSet<Card>()

    /** Load cards from a String */
    void load(String data) {
        load( new StringReader( data ) )
    }

    /** Load cards from a Reader */
    void load(Reader stream) {
        log.info( "Clearing stored cards" )
        cards.clear()

        for ( row in CsvParser.parseCsv(stream) ) {
            def card = new Card( row.deck, row.produces, row.requires, row.value )
            log.info( "Add ${card}" )
            cards.add( card )
        }
    }
    
    /** Load all cards from Splendor */
    static Cards getSplendor() {
        Cards all = new Cards()
        all.load(new InputStreamReader(Cards.class.classLoader.getResourceAsStream('cards.csv')))
        return all
    }
}
