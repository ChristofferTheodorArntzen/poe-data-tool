/* eslint-disable no-unused-vars */

import React from "react";
import { Link } from "react-router-dom";
import Button from "@material-ui/core/Button";
import SubscriptionForm from "./SubscriptionForm";
import SubscriptionList from "./SubscriptionList";

import "../../styles/SubscriptionMenu.css";

const SubscriptionMenu = () => {
	return (
		<main>
			<div className="subscription-view-root">
				<div>
					<SubscriptionList />
				</div>
			</div>
		</main>
	);
}

export default SubscriptionMenu;
