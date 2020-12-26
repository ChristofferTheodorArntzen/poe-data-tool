import {React , Component} from 'react';
import './App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
//import TabView from './TabView';
import LoggedInStatus from './LoggedInStatus';
import axios from 'axios';
import TabView from './TabView';

const url = 'http://localhost:3000/logged_in';

export default class Apps extends Component {
  constructor(){
    super();

    this.state = {
      loggedInStatus: 'NOT_LOGGED_IN',
      user: {}
    };

    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  checkLoginStatus(){
    axios
      .get(url, {withCredentials: true})
      .then(
        response => {
          if( response.data.logged_in && this.state.loggedInStatus === 'NOT_LOGGED_IN' ) {
            this.setState({
              loggedInStatus: 'LOGGED_IN',
              user: response.data.user
            });
          } else if (!response.data.logged_in & (this.state.loggedInStatus === 'LOGGED_IN')){
            this.setState({
              loggedInStatus: 'NOT_LOGGED_IN',
              user: {}
            })
          } 
        })
      .catch(error => {
        console.log("Check login error", error);
      });
  }
  
  componentDidMount() {
    this.checkLoginStatus();
  }

  handleLogout() {
    this.setState({
      loggedInStatus: 'NOT_LOGGED_IN',
      user: {}
    });
  }

  handleLogin(data) {
    this.setState({
        loggedInStatus: 'LOGGED_IN',
        user: data.user
    });
  }

  render() {
      return (
        <div className='app'>
          <BrowserRouter>
            <Switch>
              <Route>
                <TabView path='/tabView'/>
              </Route>
              <Route>
                exact
                path{'/LoggedIn'}
                render={ props => (
                  <LoggedInStatus
                   {...props}
                   loggedInStatus={this.state.loggedInStatus}
                  />
                )}
              </Route>
            </Switch>
          </BrowserRouter> 
            <h1>Home: </h1>
            <h1>Test: </h1>
        </div>
      )
    }

}

// https://www.valentinog.com/blog/redux/

