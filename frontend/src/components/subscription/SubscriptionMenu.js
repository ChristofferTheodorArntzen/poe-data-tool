/* eslint-disable no-unused-vars */

import React, { useState, useEffect, useContext, useMemo } from 'react';
import SubscriptionFormDialog from './SubscriptionFormDialog';

import '../../styles/SubscriptionMenu.css';
import { List, Typography } from '@material-ui/core';
import { getSubscriptions, setSubscriptionActive, deleteSubscription } from '../../adapters/SubscriptionAdapter';
import SubscriptionListItem from './SubscriptionListItem';
import { connectionContext } from '../../contexts/ConnectionContext';
import { subscriptionContext } from '../../contexts/SubscriptionContext';

import Button from '@material-ui/core/Button';

const SubscriptionMenu = () => {

	const [subscriptionArray, setSubscriptions] = useState(null);

	const subscriptionState = useMemo(() => ({ subscriptionArray, setSubscriptions }), [subscriptionArray, setSubscriptions])

	const { isConnected } = useContext(connectionContext);

	const fetchData = async () => {
		const subs = await getSubscriptions();
		setSubscriptions(subs);
	}

	useEffect(() => {
		if (!isConnected) {
			return;
		}
		try {
			fetchData();
		} catch (err) {
			console.log(err);
		}
	}, [isConnected]);

	const updateSetActiveSubscriptions = (subResponse) => {
		setSubscriptions((previousSubscription) => {
			if (previousSubscription.pk == subResponse.pk) {
				previousSubscription = subResponse;
			}
			return [...previousSubscription]
		});
	}

	const updateDeleteSubscription = (deletedSub) => {
		setSubscriptions((prevSubs) => {
			return prevSubs.filter((sub) => sub.pk !== deletedSub.pk);
		})

	}

	const handleSetActive = async (subscription) => {
		const responseData = await setSubscriptionActive(subscription);
		if (responseData) {
			updateSetActiveSubscriptions(responseData);
		}
	}

	const handleDelete = async (subscription) => {
		const responseData = await deleteSubscription(subscription);
		if(responseData) {
			updateDeleteSubscription
		} 
	}

	const constructSubscriptionRows = (subscriptions) => {

		if (subscriptions == null) return [];

		// console.log('constructSubscriptionRows');
		// console.log(subscriptions);

		const rows = subscriptions.map((sub) => {
			return (
				<SubscriptionListItem
					key={sub.pk}
					subscription={sub}
					handleSetActive={handleSetActive}
					handleDelete={handleDelete}
				/>
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
					<subscriptionContext.Provider value={subscriptionState}>

						<List overflow='scroll'>
							{constructSubscriptionRows(subscriptionArray)}
						</List>
					<SubscriptionFormDialog />
					</subscriptionContext.Provider>
				</div>
			</div>
		</main>
	);
}

export default SubscriptionMenu;
