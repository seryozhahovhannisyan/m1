/* Compiled with love by Gruntfile.js */
var Country = [
 USA = {    "id": 1,   "name":  "USA" ,   "abr": "us",   "phoneCode":  "1"  },
 ARMENIA = {    "id": 2,   "name":  "Armenia",   "abr": "am",   "phoneCode":  "374"  }

]
var valueOfCountry = function (id) {
    for(var t in Country){
        if(Country[t].id == id){
            return Country[t];
        }
    }
    return null;
}