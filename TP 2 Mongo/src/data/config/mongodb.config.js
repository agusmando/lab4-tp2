const mongoose = require("mongoose");
require("dotenv").config();

const connectMongo = () => {
    mongoose.connect(process.env.CONNECTION_LINK);
};

module.exports = { connectMongo };