import React, { Component } from "react";
import Button from "@material-ui/core/Button";
import AddAlertIcon from "@material-ui/icons/AddAlert";
import { Link } from "react-router-dom";

class SubscriptionButton extends Component {
	constructor() {
		super();
	}

	render() {
		return (
			<div className="headerIcon">
				<Button
					variant="outlined"
					size="small"
					onClick={() => {}}
					startIcon={<AddAlertIcon />}
					component={Link}
					to={"/subscriptionView"}
				>
					subscription
				</Button>
			</div>
		);
	}
}

export default SubscriptionButton;
