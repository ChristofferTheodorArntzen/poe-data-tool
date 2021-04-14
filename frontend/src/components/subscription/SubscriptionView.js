
import React, { Component } from "react";
import { Link } from "react-router-dom";
import Button from "@material-ui/core/Button";
import SubscriptionForm from "./SubscriptionForm";
import SubscriptionList from "./SubscriptionList";

import "./SubscriptionMenu.css";

class SubscriptionMenu extends Component {
	constructor() {
		super();
	}

	componentDidMount() {}
	/*
        handle submit
        - loade in data fra endepunkt
		- vise data i 
		
		todo - remove eslint - no - unused vars  
		how to keep state on this thing?
    */

	render() {
		return (
			<main>
				<div className="subscription-view-root">
					<div className="back-button">
						<Button component={Link} to="/">
							Back
						</Button>
					</div>
					<div className="subscription-view-container">
						<SubscriptionForm />
					</div>
					<div className="subscription-list">
						<SubscriptionList />
					</div>
				</div>
			</main>
		);
	}
}

export default SubscriptionMenu;
