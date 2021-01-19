# Post mortem

### Inledning

> Här beskriver du kortfattat arbetets syfte/mål, arbetssätt, genomförande. Vad är det för något du ska utveckla? Berätta om din inspiration och idéer.

Syftet med arbetet var att omarbeta mitt loggboksprogram för att förbättra det och ge mig en möjlighet att lära mig 
av den kod jag skrivit. En av de funktionerna jag ville lägga till i mitt program var redigeringsmöjligheter, alltså 
att användaren ska kunna redigera sina inlägg när den vill. Inspiration till detta fick jag från andra loggboksappar 
som till exempel Google Keep. Under projektet bestod mitt arbetssätt i att beta av ett kort från planeringsbrädet i 
taget och implementera de förändringar jag framlagt där.

### Bakgrund

> Här beskriver du den teknik du har använt och motiverar detta. Berätta om ditt planeringsarbete. Redovisa din planering och dess delar. Tester och feedback-arbete.

För att få projektet på fötter började jag med att se över koden jag skrivit till den första
versionen av programmet för att kunna identifiera potentiella brister/förbättringsområden. Medan jag gjorde detta
skapade jag en planering över vad jag skulle göra i ett kanban-bräde. När jag hade sett över koden gick jag vidare
till att fundera kring vilka funktioner jag skulle vilja lägga till i programmet. En sådan var databasintegration,
vilket jag fick inspiration till från uppgiftsbeskrivningen.

För att göra projektet mer överskådligt och ge det en tydligare struktur använde jag mig av 
Model-View-Controller-systemet. Genom att göra detta kunde jag lättare felsöka min kod då det går fort att 
identifiera vilken del av MVC som strular. När jag hade identifierat var problemet uppstod blev det lättare att 
hitta igen vad det berodde på eftersom jag då bara behövde leta igenom 1/3 av koden. Hade jag byggt hela programmet 
med en del som styrde allt hade jag behövt leta igenom hela koden för att identifiera felet.

För att underlätta indelningen av de olika delarna i MVC använde jag objektorienterad programmering. Jag kunde till 
exempel dela upp de tre stora delarna i MVC i separata klasser som jag sedan kunde splittra i fler, mindre klasser. 
Detta underlättade återigen felsökningsarbetet och gjorde det lättare för mig att bygga vidare på projektet med nya 
funktioner eftersom att jag kunde jobba med små, tydligt avgränsade (genom klasser och metoder) delar av koden.

### Positiva erfarenheter

> Här beskriver du vad som har gått bra i ditt projekt och analyserar varför.

Det som gått bra för mig har varit att jobba med den objektorienterade modellen, MVC, samt läsbarheten av koden.

Den objektorienterade delen gick bra för mig eftersom jag har en del erfarenhet av den sedan tidigare. Vi har jobbat 
med den i både PRG1 och PRG2, och jag har även jobbat med den när jag löst problem på fritiden, bland annat från 
AdventOfCode. Jag har en bra förståelse för hur objektorientering fungerar, vad ett objekt är och hur man kan 
utnyttja objekt för att få en tydligare struktur och läsbarhet i sitt program. Jag har en fungerande förståelse för 
vad som bör vara en klass och inte samt olika objektorienterade begrepp i Java, så jag har lätt för att söka efter 
hjälp på internet om det skulle behövas.

Att jobba med MVC har gått bra för mig på grund av att jag, som sagt, har en ganska bra grund i objektorienterad 
programmering. MVC-tänket är väldigt likt klassindelning av ett program, så jag kunde lätt förstå varför man delar 
upp programmet som man gör och vad varje del av programmet bör göra. Jag har även en bra förståelse av hur klasser 
kan kommunicera med varandra sedan tidigare, vilket har underlättat arbetet med MVC då det i princip är klasser som 
pratar med varann.

Att skriva bra/läsbar kod är något jag har varit intresserad av att kunna göra och alltså något jag försökt lära mig 
på fritiden. Jag såg en väldigt bra presentation om hur man bör göra för att skriva bra kod och vilka principer man 
bör utgå ifrån. Jag har försökt följa dessa principer i mitt arbete och anser att det har gått bra. En av de 
viktigaste principerna jag lärde mig om var att hålla all kod i en metod på samma abstraktionsnivå, vilket man 
oftast gör genom att extrahera metoder från andra metoder tills det inte finns någon rimlig anledning att fortsätta 
extrahera. Jag har inte hunnit med att göra detta för alla bitar av min kod, men jag tycker att mitt arbete följer 
principen till stor del.

### Negativa erfarenheter

> Här beskriver du det som du anser har gått mindre bra med ditt projekt och analyserar hur du kan undvika detta i framtida projekt.

En av de delar som gick mindre bra för mig var databasintegrationen. Det gick dåligt för mig eftersom det i 
stort har varit första gången jag jobbat med databaser i Java. Jag hade svårt att synka variabler för ID:n mellan 
databasen och programmet, samt att spara användare och inlägg till databasen. När jag skulle spara inlägg fick jag 
problemet att vissa inlägg hade samma ID som andra, något jag inte lyckades lösa. En potentiell lösning jag inte 
hann testa till detta är att spara inläggen direkt till databasen så fort de skapas och på så sätt undvika att 
behöva synka ID:n mellan databasen och programmet. Jag tror att problemen med databasdelen till stor del beror på 
mina bristande kunskaper i MySQL i allmänhet (databasen som användes) och faktumet att jag inte 
tidigare jobbat utförligt med MySQL i Java. 

### Sammanfattning

> Här redovisar du dina slutsatser, erfarenheter och lärdomar. Reflektera över din produkt och dess/dina utvecklingsmöjligheter. Vad kan vidareutvecklas och finns det utrymme att bygga vidare på projektet?

När det gäller produktens utvecklingsområden är det tydligaste, och enligt mig viktigaste, databasintegrationen. 
Utan en fungerande koppling till en databas är programmet helt oanvändbart, vilket det alltså är i nuläget. Man kan 
skapa sina inlägg men vill man spara dem använder man i det här läget fel mjukvara. Så, den viktigaste förbättringen 
jag kan göra på det här projektet är att lösa problemen med databasen.

De delar jag hade löst annorlunda är främst databasintegrationen och möjligtvis redigeringen. Just databasbiten har 
jag redan diskuterat en del, men det huvudsakliga problemet med den är sättet jag valde att spara data till den. I 
den första versionen av programmet använde jag mig av Javas egna filsparning, programmet skrev alltså all data 
klasserna innehöll till en fil och återskapade sedan sig självt från den filen. När jag jobbade med detta bestämde 
jag mig för att behålla alla nya inlägg och användare i minnet tills användaren valde att spara dem och stängde ner 
programmet. Den lösningen funkade bra eftersom programmet ändå behövde "ta en bild av sig självt" för den 
sparmetoden, och när programmet stängs ner kan man vara säker på att användaren skrivit klart sina inlägg. När det 
kommer till databaser är dock själva sparningen och programmet separerade, vilket gör att den lösningen blir 
onödig/ickefungerande. Det hade istället varit lättare att skriva inläggen till databasen direkt användaren var klar 
med dem för att hålla databasen uppdaterad och låta den sköta hanteringen av ID:n. Då slipper man försöka synka 
ID-räknare mellan programmet och databasen, något jag försökte med länge. Här skulle jag ha insett att min lösning 
förmodligen inte var så passande och gått vidare till andra sätt att hantera problemet.

Redigeringsfunktionen blev aldrig färdigställd, men jag har en känsla av att jag borde ha löst det på ett annat sätt 
än det jag hade tänkt. Det jag definitivt borde ha gjort var att läsa på om versionshantering/redigering innan jag 
slängde ihop en plan. Då hade jag fått en bättre bild av hur dagens system funkar och därigenom förmodligen fått det 
lättare att bygga mitt egna system, istället för att försöka lösa allt från grunden.

Som jag nämnt gick mycket av min tid till att försöka lösa problemen med databasen, och jag har även nämnt vad jag 
borde gjort istället. Något annat jag dock anser att jag la för mycket tid på var refaktoriseringen av koden. Det är 
självklart viktigt att skriva bra kod som andra kan läsa, något jag tagit upp, men det är dumt att börja finslipa de 
delar man har gjort klart innan man börjar jobba på de delar som kvarstår. Jag började det här projektet med att 
refaktorisera min gamla kod, när jag borde börjat med att t.ex integrera databasen.

Det jag känner att jag behöver bli bättre på är hur man bör hantera databaser. Jag hade svårt att förstå hur man bör 
kommunicera med en databas från ett program medan användaren använder programmet då jag tidigare inte arbetat med 
särskilt dynamiska program. Jag behöver även bli bättre på att prioritera min tid och inse att jag definitivt borde 
lägga tid på att skriva om min kod och göra den bättre, men endast när jag faktiskt blivit klar med de viktigaste 
delarna i projektet. Det finns olika skolor när det kommer till sånt här, vissa tycker att det är viktigt att 
refaktorisera sin kod så fort man skrivit den så att det sedan blir lättare att bygga vidare på den, medan andra 
anser att det är bättre att få alla funktioner på plats och sedan skriva om. Jag tycker mer om det första 
tankesättet, men det lämpar sig bättre till större, mer långvariga projekt där produktens kvalite och 
vidareutvecklingsmöjligheter är viktig. När det kommer till skolarbeten är det viktiga att lära sig av det man gör 
och att visa att man förstått den teknik man lärt sig under det projekt man jobbat med, vilket man gör lättast genom 
att få alla bitar på plats först. 