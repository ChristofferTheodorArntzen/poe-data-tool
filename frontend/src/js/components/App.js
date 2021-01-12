/* eslint-disable no-unused-vars */
import { React, Component } from "react";
import Header from "./Header/Header";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import { createMuiTheme, ThemeProvider } from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import ValuableItemContainer from "./valuableItemContainer/ValuableItemContainer";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import SubscriptionMenu from "./SubscriptionView/SubscriptionView";
import LoginView from "./LoginView/LoginView";
import "./App.css";

class App extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
		};
	}

	render() {
		const darkTheme = createMuiTheme({
			palette: {
				type: "dark",
			},
		});

		return (
			<div className="app">
				<Router>
					<ThemeProvider theme={darkTheme}>
						
						<Header />
	
						<Route exact path="/">
							<ValuableItemContainer />
						</Route>
						{/* <Route  component={ValuableItemContainer} /> */}

						<Route path="/subscriptionView" component={SubscriptionMenu} />
	
						<Route path="/LoginView" component={LoginView} />
					</ThemeProvider>
				</Router>
			</div>
		);
	}
	
}

export default App;