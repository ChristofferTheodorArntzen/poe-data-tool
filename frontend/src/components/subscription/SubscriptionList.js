/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from "react";
import List from "@material-ui/core/List";
import SubscriptionListItem from "./SubscriptionListItem";
import Typography from "@material-ui/core/Typography";
import { getSubscriptions } from '../../adapters/SubscriptionAdapter';

const SubscriptionList = () => {

	const [subscriptions, setSubscriptions] = useState([]);
	const [hasError, setHasError] = useState(false);

	useEffect( async () => {
		const response = await getSubscriptions();
		
		if(response != 200) return;

		setSubscriptions(response.data);

	}, [])

	

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
					<SubscriptionListItem subscriptions={subscriptions} />
				</div>
			</List>
		</div>
	);
}

export default SubscriptionList;
