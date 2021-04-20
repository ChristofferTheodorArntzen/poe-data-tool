/* eslint-disable no-unused-vars */

import React, { useState, useEffect, useContext } from 'react';
import SubscriptionForm from './SubscriptionForm';

import '../../styles/SubscriptionMenu.css';
import { List, Typography } from '@material-ui/core';
import { getSubscriptions } from '../../adapters/SubscriptionAdapter';
import SubscriptionListItem from './SubscriptionListItem';
import { connectionContext } from '../../contexts/ConnectionContext';

const SubscriptionMenu = () => {

	const [subscriptionArray, setSubscriptions] = useState(null);

	const { isConnected } = useContext(connectionContext);

	const fetchData = async () => {
		const subs = await getSubscriptions();
		setSubscriptions(subs);
	}

	useEffect(async () => {

		if(!isConnected) {
			return;
		}

		try {
			fetchData();
		} catch (err) {
			console.log(err);
		}
	}, []);

	const constructSubscriptionRows = (subscriptions) => {

		if (subscriptions == null) return [];
	
		const rows = subscriptions.map((sub) => {
			return (
				<SubscriptionListItem key={sub.pk} subscription={sub} />
			)
		})
	
		return rows;
	}
	
	return (
		<main>
			<div className='subscription-view-root'>
				<div className='subscription-list'>
				<Typography
					variant='h4'
					component='h3'
					className='subscription-list-header'
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
