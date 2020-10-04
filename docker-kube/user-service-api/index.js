const express = require("express");
const app = express();
const PORT = 5000;

app.get("/", (req, res) => {
	res.json([
		{
			name: "Bob",
			email: "bob@gmail.com"
		},
		{
			name: "Jack",
			email: "jack@yahoo.com"
		},
		{
			name: "Alice",
			email: "alice@hotmail.com"
		}
	]);
});

app.listen(PORT, () => {
	console.log(`Server listening on port ${PORT}...`);
});
