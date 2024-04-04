const mongoose = require("mongoose");

const connectMongo = () => {
    mongoose.connect(
        process.env.CONNECTION_LINK, {
            useNewUrlParser: true,
            useUnifiedTopology: true,
        },
        (err, db) => {
            if (err)
                console.log(
                    "Ocurrió un error al conectarse a la base de datos " + error
                );
        }
    );
};

module.export = { connectMongo };