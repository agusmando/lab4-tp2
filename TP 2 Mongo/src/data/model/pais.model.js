const mongoose = require("mongoose");

const paisSchema = new mongoose.Schema({
    nombrePais: {
        type: String,
        required: true,
    },
    capitalPais: {
        type: String,
        required: true,
    },
    region: {
        type: String,
        required: true,
    },
    poblacion: {
        type: Number,
        required: true,
    },
    latitud: {
        type: Number,
        required: true,
    },
    longitud: {
        type: Number,
        required: true,
    },
    codigoPais: {
        type: Number,
        required: true,
        index: true,
    },
});

module.exports = mongoose.model("Pais", paisSchema);