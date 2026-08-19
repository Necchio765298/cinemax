Necchio Arianna, Matricola: 765298, sede: Como

INDICAZIONI SULL'INSTALLAZIONE, COMPILAZIONE ED ESECUZIONE DI CINEMAX
L'applicazione cineMax si occupa di gestire una sala cinema e gli eventi ad essa associati: a seconda del ruolo degli utenti registrati, 
si hanno le seguenti funzionalità:
- inserire, modificare o eliminare le proiezioni nel palinsesto;
- possibilità di prenotare posti per i clienti registrati;
- creare, modificare e eliminare le prenotazioni;
- ricercare una proiezione;
e altre possibilità.

REQUISITI
È necessario disporre di una versione di Java pari o successiva a Java 17 (JDK 17). 

INSTALLAZIONE (DOWNLOAD DELLA JAVA VIRTUAL MACHINE)
1. recarsi all’indirizzo https://www.oracle.com/it/java/technologies/downloads/
2. scegliere la versione di Java desiderata 
3. scegliere il sistema operativo per cui scaricare l’ambiente
4. cliccare sul link per scaricare l'eseguibile

INSTALLAZIONE (IMPOSTARE E CONFIGURARE LA JAVA VIRTUAL MACHINE)
1. aprire la cartella appena scaricata
2. recarsi in “Java”
3. spostarsi sulla cartella denominata con “jdk-numero_versione”
4. aprire la directory “bin”
5. copiarne il classpath
PROCEDURA PER WINDOWS (simile su altri SO)
6. avviare le Impostazioni
7. recarsi su “sistema” 
8. cliccare su “informazioni sul sistema”
9. accedere alle impostazioni di sistema avanzate
10. aprire la scheda “Variabili d’ambiente”
11. cliccare su “Variabili di sistema”
12. selezionare “Path”
13. cliccare su “Modifica”
14. cliccare su "Nuovo"
15. Incollare il classpath copiato e salvare

INSTALLAZIONE (SCARICARE IL PROGRAMMA)
1. recarsi su https://github.com/Necchio765298/cinemax.git
2. cliccare su “Code”
3. cliccare su “Download ZIP”

COMPILAZIONE
1. scompattare l'archivio scaricato
2. collocarsi all’interno della cartella “cinemax”
3. digitare “cmd” all'interno della barra di navigazione della cartella
4. digitare il comando javac -d bin src/cinemax/*.java
5. digitare il comando jar cvfe cinemax.jar cinemax.cineMax -C bin cinemax data

ESECUZIONE
1. digitare il comando java -jar bin/cinemax.jar