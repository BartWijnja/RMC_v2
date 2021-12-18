# Rent my Car

Om duurzamer vervoer te stimuleren wordt een platform gemaakt om het delen van autogebruik te bevorderen. De gebouwde applicatie geeft de mogelijkheid aan gebruikers:

om een eigen auto aan te bieden voor verhuur aan andere gebruikers.
om een auto te reserveren en te huren voor een dagdeel.
De applicatie bestaat uit twee onderdelen:

een Rest api functionaliteit biedt om gegevens van te huur aangeboden auto's en beschikbaarheid hiervan te beheren.
een Android app om auto's toe te voegen en om beschikbare auto's te zoeken en reserveren.

## Functionaliteit

- De gebruiker kan kiezen voor het aanbieden van een eigen auto of het huren van een auto.
- Bij het aanbieden van een auto voor verhuur kunnen gegevens ingevoerd worden van de auto (zoals merk, type, ...) en gegevens over de verhuur voorwaarden (prijs, ophalen/inleveren). Van een aangeboden auto kan met de camera van de mobiel foto's gemaakt worden en toegevoegd worden aan de gegevens van de auto.
- Er wordt onderscheid gemaakt tussen de volgende categorieën auto's:
    - ICE (interne verbrandingsmotor): brandstof auto op benzine, diesel of gas
    - BEV (Battery Electric Vehicle) - elektrische auto met accu
    - FCEV (Fuel Cell EV) - elektrische auto met brandstofcel waarin waterstof wordt omgezet in elektrische energie. 
- Voor toegevoegde auto's kan de eigenaar:
    - de TCO (Total Cost of Ownership) laten berekenen op basis van een standaard aantal km's per jaar.
    - de verbruikskosten per km berekenen. De berekening hiervan is per categorie (en brandstoftype) verschillend. 
- Voor het huren van een auto kan gefilterd worden op criteria (bijvoorbeeld: afstand of tarief). De collectie van geschikte auto's wordt getoond in een lijstweergave of op de         kaart (map). Vervolgens kan de gebruiker een auto huren door deze te selecteren en een beschikbaar tijdsblok te selecteren.
- Een huurder van een auto kan de (loop)route opvragen naar de huurauto vanaf de huidige locatie.
- Tijdens het rijden worden gegevens bijgehouden in de applicatie over gereden afstand en rijgedrag (acceleratie bij optrekken en afremmen). Een rustig rijgedrag wordt beloond         door bonuspunten toe te kennen aan de huurder van auto.

