import React, { Component } from "react";

import SettingsButton from "./HeaderIcons/SettingsButton";
import SubscriptionButton from "./HeaderIcons/SubscriptionButton";
import LoginButton from "./HeaderIcons/LoginButton";
import TestAlertButton from "./HeaderIcons/TestAlertButton";
import Typography from "@material-ui/core/Typography";

import "../../styles/header.css";

/*
    Add button handle for when view is smaller - hamburbur thingy
*/

class Header extends Component {
	constructor() {
		super();
	}

	componentDidMount() {}

	render() {
		return (
			<header className="header">
				<div className="header-title">
					<Typography variant="h5" component="h5"> PoE - Private Stash Tab Estimator </Typography>
				</div>
				<div className="header-icon-container">
					<SettingsButton />
					<SubscriptionButton />
					<LoginButton />
					<TestAlertButton />
				</div>
			</header>
		);
	}
}

export default Header;
