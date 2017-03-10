/* Compiled with love by Gruntfile.js */
var PartitionLCP = [
 CONNECT_TO_TV = {    "id": 1,   "name":   "connecttotv",   "dns":          "connecttotv.com",   "host":   "https://www.connecttotv.com",   "language":   Language.ENGLISH  },
 HOLLOR = {    "id": 8,   "name":   "hollor",   "dns":               "hollor.com",   "host":        "https://www.hollor.com",   "language":   Language.ENGLISH  },
 VSHOO_LA = {    "id": 13,   "name":  "Vshoo LA",   "dns":             "",   "host":                  "https://www.vshoo.com",   "language":   Language.ENGLISH  },
 VSHOO_YEREVAN = {    "id": 14,   "name":  "Vshoo Yerevan",   "dns":        "",   "host":                  "https://www.vshoo.am",   "language":   Language.ARMENIAN  },
 VSHOO_USA = {    "id": 15,   "name":  "Vshoo USA",   "dns":            "vshoo.com",   "host":         "https://www.vshoo.com",   "language":   Language.ENGLISH  },
 VSHOO_ARMENIA = {    "id": 16,   "name":  "Vshoo Armenia",   "dns":        "vshoo.am",   "host":          "https://www.vshoo.am",   "language":   Language.ARMENIAN  },
 VSHOO_ARMENIA_REGIONS = {    "id": 17,   "name":  "Vshoo Armenia Regions",   "dns": "",   "host":                 "https://www.vshoo.am",   "language":   Language.ARMENIAN  },
 VSHOO_IRAN = {    "id": 21,   "name":  "Vshoo Iran",   "dns":           "vshoo.ir",   "host":          "https://www.vshoo.ir",   "language":  Language.PERSIAN  }

]
var valueOfPartitionLCP = function (id) {
    for(var t in PartitionLCP){
        if(PartitionLCP[t].id == id){
            return PartitionLCP[t];
        }
    }
    return null;
}