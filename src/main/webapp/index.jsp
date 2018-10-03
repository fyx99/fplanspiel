<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
    <h2>Beispiel Rest!</h2>
    <p><a href="api/spiel/ping?ping=84">Ping</a></p>
    <p>neues spiel unternehmen hinzufügen -> spielstarten, 1. simulation -> jz erster u dran für actions</p>
    <p><a href="api/spiel/quickstart">quick</a></p>
    <p><a href="api/spiel/neuesspiel">neues Spiel</a></p>
    <p><a href="api/spiel/neuesunternehmen/abcag/A">neues Unternehmen mit name ABC AG mit Standort A</a></p>
    <p><a href="api/spiel/neuesunternehmen/abckgaa/B">neues Unternehmen mit name ABC KGAA mit Stanndort B</a></p>
    <p><a href="api/spiel/spielstarten?rundenZahl=5">spiel starten</a></p>
    <p><a href="api/spiel/zugbeendet">zug beendet</a></p>
    <p>Märkte  <a href="api/spiel/mmarkt">m</a>  <a href="api/spiel/bmarkt">b</a>  <a href="api/spiel/vmarkt">v</a>  <a href="api/spiel/fmarkt">f</a>  <a href="api/spiel/amarkt">a</a></p>
    <div>
    	<input id="id" /><input id="menge" /><button onclick="kaufen()">kaufen</button>
    	<script>
    		function kaufen(){
    			var id = document.getElementById("id").value;
    			var menge = document.getElementById("menge").value;
    			jQuery.ajax({
    				url: "api/spiel/angebotkaufen?menge=" + menge + "&angebotsid=" + id,
    				type:"GET"
    			});
    		}
    	</script>
    </div>
        <div>
    	<input id="id2" /><input id="menge2" /><button onclick="produzieren()">produzieren</button>
    	<script>
    		function produzieren(){
    			var id = document.getElementById("id2").value;
    			var menge = document.getElementById("menge2").value;
    			jQuery.ajax({
    				url: "api/spiel/produziere?menge=" + menge + "&maschinenid=" + id,
    				type:"GET"
    			});
    		}
    	</script>
    </div>
        <div>
    	<input id="id3" /><input id="menge3" /><input id="preis3" /><button onclick="anbieten()">anbieten</button>
    	<script>
    		function anbieten(){
    			var id = document.getElementById("id3").value;
    			var menge = document.getElementById("menge3").value;
    			var preis = document.getElementById("preis3").value;
    			jQuery.ajax({
    				url: "api/spiel/anbieten?menge=" + menge + "&produktid=" + id + "&preis=" + preis,
    				type:"GET"
    			});
    		}
    	</script>
    </div>
    <p><a href="api/spiel/maschinen">maschinen</a></p>
    <p><a href="api/spiel/materialien">material</a></p>
    <p><a href="api/spiel/produkte">produkte</a></p>
    <p><a href="api/spiel/umsatzhistorievmarkt">umsatzhistorievmarkt</a></p>
    <p><a href="api/spiel/unternehmen">unternehmen</a></p>
    <p><a href="api/spiel/zwischenstand">zwischenstand</a></p>
    <p><a href="api/spiel/rundenresultat">rundenresultat</a></p>
    

</body>
</html>