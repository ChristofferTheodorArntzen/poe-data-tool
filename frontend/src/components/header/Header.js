/* eslint-disable no-unused-vars */
import React, { useContext } from "react";

import SettingsButton from "./HeaderIcons/SettingsButton";
import SubscriptionButton from "./HeaderIcons/SubscriptionButton";
import LoginButton from "./HeaderIcons/LoginButton";
import TestAlertButton from "./HeaderIcons/TestAlertButton";
import Typography from "@material-ui/core/Typography";

import "../../styles/header.css";
import { userContext } from "../../contexts/UserContext";
import FeedButton from "./HeaderIcons/FeedButton";

const Header = () => {

	const { user } = useContext(userContext);

	return (
		<header className="header">
			<div className="header-title">
				<Typography variant="h5" component="h5">
					PoE - Private Stash Tab Estimator
				</Typography>
			</div>
			<div className="header-icon-container">
				<FeedButton />
				<SubscriptionButton />
				<LoginButton />
				<TestAlertButton />
				<SettingsButton />
				{/* TODO: add a logged in icon to taskbar */}
				<div style={{ color: 'white' }}>
					{(user != null) ? user.accountName : 'null'}
				</div>
			</div>
		</header>
	);

}

export default Header;
