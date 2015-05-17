package com.akonizo.splendor.data

enum Gem {
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

    Gem ( String a ) {
        this.abbrev = a[0]
    }

    static get( String id ) {
        return get( (char) id[0] )
    }

    static get( Character id ) {
        return map[ id.toUpperCase() ]
    }
}
