/* eslint-disable no-unused-vars */
import React, { Component } from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Button from "@material-ui/core/Button";
import SubscriptionForm from "./SubscriptionForm";

import "./SubscriptionMenu.css";

class SubscriptionMenu extends Component {
	constructor() {
		super();
	}

	componentDidMount() {}

	/*
    
        legge til på subscriptionen
            - navn
            - treshold type
            - treshold quantity
            - filter for hva subscription filtrere vekk av item typer


        handle submit

        - loade in data fra endepunkt
        - vise data i 

        todo - remove eslint - no - unused vars  
    */

	render() {
		return (
			<main>
				<div className="subscription-menu-root">
					<div className="back-button">
						<Button component={Link} to="/">
							Back
						</Button>
					</div>
					<div className="subscription-menu-container">
						<SubscriptionForm />
					</div>
					<div className="current-data-view"></div>
				</div>
			</main>
		);
	}
}

export default SubscriptionMenu;
