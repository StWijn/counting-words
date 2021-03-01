# Simple instructions
Project is getest m.b.v. Eclipse en IntelliJ met Tomcat V9 als server, tevens zijn V8.5, V8 en V7 compatible. Latere versies van WildFly zouden ook als server kunnen dienen.

Afhankelijk van de manier van importeren en de settings van de IDE zal de context root in de meeste gevallen "counting-words" zijn. De uri's zijn gesuffixed met /api/* (is aan te passen) waarbij de Path's in WordResource de REST endpoints aanduiden.
Hier een voorbeeld uri: http://localhost:8080/counting-words/api/calc/input text

Code logic wordt getest in WordFrequencyAnalyzerImplTest.class m.b.v. JUnit 5 en REST functionaliteiten in WordResourceTest m.b.v. JerseyTest en JUnit5.
