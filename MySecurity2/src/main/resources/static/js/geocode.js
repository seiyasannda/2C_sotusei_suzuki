// geocode.js
var geocoder;
var map;

function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(35.697456, 139.702148);
    var opts = {
        zoom: 10,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), opts);

    // codeAddresses関数を呼び出す
    codeAddresses();
}

function codeAddresses() {
    var addresses = document.getElementById("address").options;

    for (var i = 0; i < addresses.length; i++) {
        codeAddress(addresses[i].text);
    }
}

function codeAddress(address) {
    if (geocoder) {
        geocoder.geocode({
                address: address,
                region: 'jp'
            },
            function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var latlng = results[0].geometry.location;
                    new google.maps.Marker({
                        position: latlng,
                        map: map
                    });
                    document.getElementById('id_ido').innerHTML = latlng.lat();
                    document.getElementById('id_keido').innerHTML = latlng.lng();
                } else {
                    alert("Geocode 取得に失敗しました。理由: " + status);
                }
            });
    }
}
