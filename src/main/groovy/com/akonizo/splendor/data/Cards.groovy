package com.akonizo.splendor.data

import groovy.util.logging.Slf4j

import com.xlson.groovycsv.CsvParser

@Slf4j
class Cards {

    /** Set of loaded cards */
    Set<Card> cards = new LinkedHashSet<Card>()

    /** Load cards from a String */
    void load(String data) {
        load( new StringReader( data ) )
    }

    /** Load cards from a Reader */
    void load(Reader stream) {
        log.debug( "Clearing stored cards" )
        cards.clear()

        for ( row in CsvParser.parseCsv(stream) ) {
            def card = new Card( row.deck, row.produces, row.requires, row.value, row.ring )
            log.debug( "Add ${card}" )
            cards.add( card )
        }
    }

    /** Find all matching cards */
    Collection<Cards> findAll( Closure closure ) {
        cards.findAll( closure )
    }

    /** Load all cards from Splendor */
    static Cards getSplendor() {
        Cards all = new Cards()
        all.load(new InputStreamReader(Cards.class.classLoader.getResourceAsStream('cards.csv')))
        return all
    }
}
