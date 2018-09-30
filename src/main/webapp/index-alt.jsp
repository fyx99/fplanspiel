<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
    <h2>Beispiel Rest!</h2>
    <p><a href="webapi/v1/demo/ping">Ping</a></p>
    <p>neues spiel unternehmen hinzufügen -> spielstarten, 1. simulation -> jz erster u dran für actions</p>
    <p><a href="webapi/v1/demo/quickstart">quick</a></p>
    <p><a href="webapi/v1/demo/neuesspiel">neues Spiel</a></p>
    <p><a href="webapi/v1/demo/neuesunternehmen/abcag">neues Unternehmen mit name ABC AG</a></p>
    <p><a href="webapi/v1/demo/neuesunternehmen/abckgaa">neues Unternehmen mit name ABC KGAA</a></p>
    <p><a href="webapi/v1/demo/spielstarten">spiel starten</a></p>
    <p><a href="webapi/v1/demo/zugbeendet">zug beendet</a></p>
    <p><a href="webapi/v1/demo/stats">stats</a></p>
    <p><a href="webapi/v1/demo/angebotkaufen/5/5">angebotkaufen/5/5</a></p>
    <div>
    	<input id="id" /><input id="menge" /><button onclick="kaufen()">kaufen</button>
    	<script>
    		function kaufen(){
    			var id = document.getElementById("id").value;
    			var menge = document.getElementById("menge").value;
    			jQuery.ajax({
    				url: "webapi/v1/demo/angebotkaufen/" + menge + "/" + id,
    				type:"GET"
    			});
    		}
    	</script>
    </div>
    <p><a href="webapi/v1/demo/produziere/2/7">produziere/2/7</a></p>
    <p><a href="webapi/v1/demo/anbieten/2/6/10">anbieten/2/6/10</a></p>
    <p><a href="webapi/v1/demo/bestand">bestand</a></p>
    <p><a href="webapi/v1/demo/maschinen">maschinen</a></p>
    <p><a href="webapi/v1/demo/materialien">material</a></p>
    <p><a href="webapi/v1/demo/produkte">produkte</a></p>
    <p><a href="webapi/v1/demo/umsatzhistorievmarkt">umsatzhistorievmarkt</a></p>
    <p><a href="webapi/v1/demo/unternehmen">unternehmen</a></p>
    <p><a href="webapi/v1/demo/produziere/20/53">produziere/2/7</a></p>
    <p><a href="webapi/v1/demo/produziere/20/53">produziere/2/7</a></p>
    
    <p><a href="webapi/v1/demo/anbieten/20/44/20">anbieten 20</a></p>
    
    <p><a href="webapi/v1/demo/anbieten/20/44/30">anbieten 20</a></p>
    
    
    <p><a href="webapi/v1/demo/log">log</a></p>

</body>
</html>