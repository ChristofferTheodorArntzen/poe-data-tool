/* eslint-disable no-unused-vars */
import React, { useState, useMemo, useEffect } from 'react';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Header from '../components/header/Header';
import ValuableItemFeed from '../components/valuableItem/ValuableItemFeed';
import SubscriptionMenu from './subscription/SubscriptionMenu';
import Login from '../components/login/Login';
import LandingPage from '../components/landingPage/LandingPage';
import Notification from './Notification/Notification';

//Adapters
import { checkHealthEndPoint } from '../adapters/HealthAdapter';
import { topicFeed, topicGenerator } from '../adapters/ValuableItemWSAdapter';

// Context imports
import { userContext } from '../contexts/UserContext'
import { connectionContext } from '../contexts/ConnectionContext';

import '../styles/App.css';

const darkTheme = createMuiTheme({
	palette: {
		type: 'dark',
	},
});

const App = () => {

	const [user, setUser] = useState(null);
	const [isConnected, setIsConnected] = useState(null);

	const userState = useMemo(() => ({ user, setUser }), [user, setUser])
	const connectedState = useMemo(() => ({ isConnected, setIsConnected }), [isConnected, setIsConnected]);

	const checkHealth = async () => {
		const health = await checkHealthEndPoint();
		setIsConnected(health);
	}

	useEffect(() => {
		try {
			const interval = setInterval(() => {
				checkHealth();
			}, 10000);

			return () => clearInterval(interval);

		} catch (err) {
			console.log(err);
		}
	}, []);

	return (
		<React.StrictMode>
			<connectionContext.Provider value={connectedState}>
				<userContext.Provider value={userState}>
					<div className='app'>
						<Router>
							<ThemeProvider theme={darkTheme}>
								<Notification showNotification={ connectedState } />
								<Header />
								<Route exact path='/' component={LandingPage} />
								<Route path='/valuableItemFeed'>
									<ValuableItemFeed webSocketTopic={topicFeed} />	
								</Route>
								<Route path='/valuableItemSimulator'>
									<ValuableItemFeed webSocketTopic={topicGenerator} />	
								</Route>
								<Route path='/subscription' component={SubscriptionMenu} />
								<Route path='/login' component={Login} />

							</ThemeProvider>
						</Router>
					</div>
				</userContext.Provider>
			</connectionContext.Provider>
		</React.StrictMode>
	)
}

export default App;