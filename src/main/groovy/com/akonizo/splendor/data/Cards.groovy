package com.akonizo.splendor.data

import groovy.util.logging.Slf4j

import com.xlson.groovycsv.CsvParser

@Slf4j
class Cards {

    Set<Card> cards = new HashSet<Card>()

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
    
    static Cards getAll() {
        Cards all = new Cards()
        all.load(new InputStreamReader(Cards.class.classLoader.getResourceAsStream('cards.csv')))
        return all
    }
}
