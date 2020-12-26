import React, { Component } from 'react';
import axios from 'axios';
import './Login.css';

const url_sessions = 'http://localhost:3001/sessions';

class LoginView extends Component {
    constructor(props) {
        super(props);

        this.state = {
                league: '',
                accountName: '',
                realm: '',
                sessionId: '' 

        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);   
    }
    
    handleChange(event) {
        const target = event.target;
        const value = target.type === 'select' ? target.option.value : target.value;
        
        const name = target.name;
        this.setState({
            [name]: value
        });
    }


    handleSubmit(event) {
        const {league, accountName, realm, sessionId } = this.state;
        
        axios
         .post(
            'http://localhost:3001/sessions',
              {
                user: {
                 league: league,
                 accountName: accountName,
                 realm: realm,
                 sessionId: sessionId
                }
              },
              { withCredentials: true }
            )
            .then(response => {
                if(response.data.logged_in) {
                    this.props.handleSuccessfulAuth(response.data);
                }
            })
            .catch(error => {
                console.log("login error", error);
            });
        event.prventDefault();
    }

    render() {
        return (
            <div className = 'form'>
                <form onSubmit={this.handleSubmit}>
                    <div>
                        <label id='label'>
                            Realm:
                        </label>
                    </div>
                    <div>
                        <input 
                            id='input'
                            name='realm'
                            type='text'
                            value={this.state.realm}
                            onChange={this.handleChange}
                            required
                            />
                    </div>
                    <br id='breakLine'/>
                    <div>
                        <label id='label'>
                            Account Name:
                        </label>
                    </div>
                    <div>
                        <input
                            id='input'
                            name='accountName'
                            type="text"
                            value={this.state.accountName}
                            onChange={this.handleChange}
                            required
                            />
                    </div> 
                    <br id="breakLine"/>
                    <div>
                        <label id="label">
                        League:
                        </label>
                    </div>
                    <div>
                    <select id="select" name="league" type='select' value={this.state.league} onChange={this.handleChange}>
                            <option value="Standard">Standard</option>
                            <option value="Hardcore">Hardcore</option>
                            <option value="TmpStandard">Temporary Standard</option>
                            <option value="TmpStandard">Temporary Standard</option>
                        </select>
                    </div>
                    <div>
                    <label id="label">
                        Session ID:
                    </label>
                    </div>
                    <div>
                        <input
                            id="input"
                            name="sessionId"
                            type="text"
                            value={this.state.sessionId}
                            onChange={this.handleChange}
                            required
                            />
                    </div>
                    <br id="breakLine"/>
                    <input type="Submit" value="submit"/>
                </form>
            </div>
        )
    }
}

export default LoginView;