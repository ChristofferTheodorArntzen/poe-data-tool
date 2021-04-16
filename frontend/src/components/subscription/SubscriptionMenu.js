/* eslint-disable no-unused-vars */

import React, { useState, useEffect } from "react";
import SubscriptionForm from "./SubscriptionForm";

import "../../styles/SubscriptionMenu.css";
import { List, Typography } from "@material-ui/core";
import { getSubscriptions } from "../../adapters/SubscriptionAdapter";
import SubscriptionListItem from "./SubscriptionListItem";


const constructSubscriptionRows = (subscriptions) => {

	if (subscriptions == null) return [];

	const rows = subscriptions.map((sub) => {
		return (
			<SubscriptionListItem key={sub.pk} subscription={sub} />
		)
	})

	return rows;
}


const SubscriptionMenu = () => {

	const [subscriptionArray, setSubscriptions] = useState(null);

	useEffect(async () => {
		try {
			const subs = await getSubscriptions();
			setSubscriptions(subs);
		} catch (err) {
			console.log(err);
		}
	}, []);

	return (
		<main>
			{/* TODO: make this use 'use styles instead' */}
			<div className="subscription-view-root">
				<div className="subscription-list">
				<Typography
					variant="h4"
					component="h3"
					className="subscription-list-header"
				>
					List of Subscriptions:
				</Typography>
					<List overflow='scroll'>
						{constructSubscriptionRows(subscriptionArray)}
					</List>
				</div>
			</div>
		</main>
	);
}

export default SubscriptionMenu;
