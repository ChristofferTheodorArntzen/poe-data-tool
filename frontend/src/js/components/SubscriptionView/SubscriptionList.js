import React, { Component } from "react";
import List from "@material-ui/core/List";
import SubscriptionListItem from "./SubscriptionListItem";
import SubscriptionDataDump from "./SubscriptionDataDump.json";
import Typography from "@material-ui/core/Typography";

class SubscriptionList extends Component {
	constructor() {
		super();
	}

	componentDidMount() {
		//TODO
	}

	render() {
		const data = SubscriptionDataDump.subscriptions;
		return (
			<div>
				<List>
					<Typography
						variant="h4"
						component="h3"
						className="subscription-list-header"
					>
						List of Subscriptions:
					</Typography>
					<div className="subscription-list-item">
						<SubscriptionListItem subscriptions={data} />
					</div>
				</List>
			</div>
		);
	}
}

export default SubscriptionList;
