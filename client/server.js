const path = require("path");
const concurrently = require("concurrently");

concurrently(
  [
    {
      command: "npm:start",
      name: "react"
    },
    {
      command: "./gradlew bootRun",
      name: "spring",
      cwd: path.resolve(__dirname, "../"),
    },
  ],
  {
    prefix: "name",
    killOthers: ["failure", "success"],
  }
).catch((error) => console.error(error));
