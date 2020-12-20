import React, {useState, Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

import './Login.css';

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
        const value = target.type === 'select' ? target.option.value :
        target.value;
        
        const name = target.name;
        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        alert('A user was submitted: ' + this.state.league);
        event.prventDefault();
    }

    render() {
        return (
            <div class = 'form'>
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
                            onChange={this.handleChange}/>
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
                            onChange={this.handleChange}/>
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
                            onChange={this.handleChange}/>
                    </div>
                    <br id="breakLine"/>
                    <input type="Submit" value="submit"/>
                </form>
            </div>
        )
    }
}

export default LoginView;