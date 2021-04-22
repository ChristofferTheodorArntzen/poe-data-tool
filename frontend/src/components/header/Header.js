/* eslint-disable no-unused-vars */
import React, { useContext } from "react";
import HeaderButton from "./HeaderButton";
import Typography from "@material-ui/core/Typography";
import Emoji from "../Emoji";
import { Link } from "react-router-dom";

//Style
import "../../styles/header.css";

// data
import { userContext } from "../../contexts/UserContext";
import { connectionContext } from "../../contexts/ConnectionContext";

// icons
import RssFeedIcon from '@material-ui/icons/RssFeed';
import PersonIcon from "@material-ui/icons/Person";
import SettingsIcon from "@material-ui/icons/Settings"
import AddAlertIcon from "@material-ui/icons/AddAlert";
import NotificationsActiveIcon from '@material-ui/icons/NotificationsActive'
import HeaderContent from "./HeaderContent";


const Header = () => {

	const { user } = useContext(userContext);
	const { isConnected } = useContext(connectionContext);

	const resizeButtonsAtWidth = 1300;
	const hideContentAtWidth = 1000;

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
				<HeaderButton
					variant='outlined'
					size='small'
					startIcon={<RssFeedIcon />}
					directoryLink='/valuableItemFeed'
					buttonText='Feed'
					reSizeWidth={resizeButtonsAtWidth}
				/>

				<HeaderButton
					variant='outlined'
					size='small'
					startIcon={<AddAlertIcon />}
					directoryLink='/subscription'
					buttonText='Subscription'
					reSizeWidth={resizeButtonsAtWidth}
				/>

				<HeaderButton
					variant='outlined'
					size='small'
					startIcon={<PersonIcon />}
					directoryLink='/login'
					buttonText='Login'
					reSizeWidth={resizeButtonsAtWidth}
				/>

				<HeaderButton
					variant='outlined'
					size='small'
					startIcon={<NotificationsActiveIcon />}
					directoryLink='/valuableItemSimulator'
					buttonText='simulator'
					reSizeWidth={resizeButtonsAtWidth}
				/>

				<HeaderButton
					variant='outlined'
					size='small'
					startIcon={<SettingsIcon />}
					directoryLink='/settings'
					buttonText='Settings'
					reSizeWidth={resizeButtonsAtWidth}
				/>

				<HeaderContent
					text={(user != null)
						? user.accountName
						: null}
					label={(user == null)
						? 'Not logged In'
						: null}
					reSizeWidth={hideContentAtWidth}
				/>
				<HeaderContent
					label='Connected:'
					text={(isConnected != null && isConnected)
						? <Emoji symbol='✔️' />
						: <Emoji symbol='❌' />}
					reSizeWidth={hideContentAtWidth}
				/>
			</div>
		</header>
	);
}

export default Header;
