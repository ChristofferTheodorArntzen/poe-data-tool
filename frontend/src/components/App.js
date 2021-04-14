import { React } from "react";
import { createMuiTheme, ThemeProvider } from "@material-ui/core/styles";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Header from "../components/header/Header";
import ValuableItemFeed from "../components/valuableItem/ValuableItemFeed";
import SubscriptionMenu from "../components/subscription/SubscriptionView";
import LoginView from "../components/login/LoginView";
import "../styles/App.css";

const darkTheme = createMuiTheme({
	palette: {
		type: "dark",
	},
});

const App = () => {
	return (
		<div className="app">
			<Router>
				<ThemeProvider theme={darkTheme}>
					<Header />
					<Route exact path="/" component={ValuableItemFeed} />
					<Route path="/subscriptionView" component={SubscriptionMenu} />
					<Route path="/LoginView" component={LoginView} />
				</ThemeProvider>
			</Router>
		</div>
	)
}

export default App;