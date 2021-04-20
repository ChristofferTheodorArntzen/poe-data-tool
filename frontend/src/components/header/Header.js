/* eslint-disable no-unused-vars */
import React, { useContext } from "react";

import SettingsButton from "./HeaderIcons/SettingsButton";
import FeedButton from "./HeaderIcons/FeedButton";
import SubscriptionButton from "./HeaderIcons/SubscriptionButton";
import LoginButton from "./HeaderIcons/LoginButton";
import TestAlertButton from "./HeaderIcons/TestAlertButton";
import Typography from "@material-ui/core/Typography";

import "../../styles/header.css";
import { userContext } from "../../contexts/UserContext";
import { connectionContext } from "../../contexts/ConnectionContext";
import Emoji from "../Emoji";
import { Link } from "react-router-dom";


const Header = () => {

	const { user } = useContext(userContext);
	const { isConnected } = useContext(connectionContext);

	return (
		<header className="header">
			<div className="header-title">
				<Link to='/' className="header-title-text">
					<Typography variant="h5" component="h5">
						The Lazy Exile
					</Typography>
				</Link>

			</div>
			<div className="header-icon-container">
				<FeedButton />
				<SubscriptionButton />
				<LoginButton />
				<TestAlertButton />
				<SettingsButton />
				{/* TODO: add a logged in icon to taskbar */}
				<div style={{ color: 'white' }} className="headerIcon">
					{/* todo - make this into it's own component - to login and show login state - remove old login button */}
					{(user != null) ? user.accountName : 'Not logged in'}
				</div>
				<div style={{ color: 'white' }} className="headerIcon">
					Connected:
					{(isConnected != null && isConnected) ? <Emoji symbol='✔️' /> : <Emoji symbol='❌' />}
				</div>
			</div>
		</header>
	);

}

export default Header;
