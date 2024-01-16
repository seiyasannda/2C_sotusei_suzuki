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
}

function codeAddress() {
    var address = document.getElementById("address").value;
    if (geocoder) {
        geocoder.geocode({
                address: address,
                region: 'jp'
            },
            function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);

                    var bounds = new google.maps.LatLngBounds();
                    for (var r = 0; r < results.length; r++) {
                        if (results[r].geometry) {
                            var latlng = results[r].geometry.location;
                            bounds.extend(latlng);
                            new google.maps.Marker({
                                position: latlng,
                                map: map
                            });

                            document.getElementById('id_ido').innerHTML = latlng.lat();
                            document.getElementById('id_keido').innerHTML = latlng.lng();
                        }
                    }
                    // マーカーの範囲に合わせる場合は以下の行をコメントアウトを外してください
                    // map.fitBounds(bounds);
                } else {
                    alert("Geocode 取得に失敗しました。理由: " + status);
                }
            });
    }
}
