package com.akonizo.splendor.data

/** Enumerate the colors of Gems */
enum Gem {
    WHITE( 'W','B','K', 0xffffff ),
    BLUE(  'B','G','W', 0x0000cc ),
    GREEN( 'G','R','B', 0x009900 ),
    RED(   'R','K','G', 0x990000 ),
    BLACK( 'K','W','R', 0x000000 )

    /** Abbreviation character for each Gem color */
    final char abbrev
    
    /** Character for preceding color */
    final char preceding
    
    /** Character for succeeding color */
    final char succeeding
    
    /** RGB color value */
    final int color

    /** Map abbreviations to Gems */
    static final Map abbreviations

    static {
        abbreviations = [:] as HashMap
        values().each { color ->
            abbreviations.put( (Character) color.abbrev, color )
        }
    }

    /** Construct a Gem */
    Gem ( String i, String s, String p, int rgb ) {
        this.abbrev = i[0]
        this.succeeding = s[0]
        this.preceding = p[0]
        this.color = rgb
    }

    /** Get the previous Gem in the color sequence */
    Gem getPrev() {
        return Gem.get( preceding )
    }
    
    /** Get the next Gem in the color sequence */
    Gem getSucc() {
        return Gem.get( succeeding )
    }
    
    /** Look up a Gem, by String */
    static get( String id ) {
        return get( (char) id[0] )
    }

    /** Look up a Gem, by character */
    static get( Character id ) {
        return abbreviations[ id.toUpperCase() ]
    }
    
    /** Return an ordered set of Gems, starting with a color */
    static List<Gem> ordering( Gem start ) {
        def results = []
        results << start
        for( Gem pos = start.succ; pos != start; pos = pos.succ ) {
            results << pos
        }
        return results
    }
}
