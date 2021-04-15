/* eslint-disable no-unused-vars */
import React, { useState, useMemo } from 'react';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Header from '../components/header/Header';
import ValuableItemFeed from '../components/valuableItem/ValuableItemFeed';
import SubscriptionMenu from './subscription/SubscriptionMenu';
import Login from '../components/login/Login';
import { userContext } from '../contexts/UserContext'

import '../styles/App.css';

const darkTheme = createMuiTheme({
	palette: {
		type: 'dark',
	},
});



const App = () => {

	const [user, setUser] = useState(null);

	const userValue = useMemo(() => ({ user, setUser }), [user, setUser])

	return (
		<React.StrictMode>
			<userContext.Provider value={userValue}>
				<div className='app'>
					<Router>
						<ThemeProvider theme={darkTheme}>
							<Header />
							<Route exact path='/' component={ValuableItemFeed} />
							<Route path='/subscriptionView' component={SubscriptionMenu} />
							<Route path='/LoginView' component={Login} />
						</ThemeProvider>
					</Router>
				</div>
			</userContext.Provider>
		</React.StrictMode>
	)
}

export default App;