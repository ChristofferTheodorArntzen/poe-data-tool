/* eslint-disable no-unused-vars */
import React, { useState, useMemo } from 'react';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Header from '../components/header/Header';
import ValuableItemFeed from '../components/valuableItem/ValuableItemFeed';
import SubscriptionMenu from './subscription/SubscriptionMenu';
import Login from '../components/login/Login';
import LandingPage from '../components/landingPage/LandingPage'

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

	return (
		<React.StrictMode>
			<connectionContext.Provider value={connectedState}>
				<userContext.Provider value={userState}>
					<div className='app'>
						<Router>
							<ThemeProvider theme={darkTheme}>
								<Header />
								<Route exact path='/' component={LandingPage} />
								<Route path='/valuableItemFeed' component={ValuableItemFeed} />
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