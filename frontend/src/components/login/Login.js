import React, { useState, useEffect, useContext } from 'react'
import {
    Container,
    makeStyles,
    Typography,
    TextField,
    Button,
} from '@material-ui/core';
import './../../styles/LoginView.css';
import { postUserInfo } from '../../adapters/LoginAdapter';
import { saveUserToLocalStorage, getUserFromLocalStorage, deleteUserFromLocalStorage } from '../../adapters/LocalStorageAdapter';
import { userContext } from '../../contexts/UserContext';

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(5),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        backgroundColor: '#404040',
        color: 'white',
        padding: theme.spacing(2),
        borderRadius: theme.spacing(1),
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    validationError: {
        color: 'red',
    },
    loggedInHeader: {
        marginTop: theme.spacing(4),
        marginBottom: theme.spacing(4),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    loggedInDiv: {
        paddingTop: theme.spacing(2),
        display: 'grid',
        gridTemplateColumns: '1fr 0.5fr',
        gridTemplateRows: '0.5fr 0.5fr 0.5fr 0.5fr',
        gap: '20px 21px',
    }
}));

const userData = {
    accountName: '',
    league: '',
    realm: '',
    sessionId: '',
}

const Login = () => {

    const [submitted, setSubmitted] = useState(false);
    const [loggedIn, setLoggedIn] = useState(false);
    const [loginInfo, setLoginInfo] = useState(userData);
    const { setUser } = useContext(userContext);

    useEffect(() => {
        const loggedInUser = getUserFromLocalStorage();
        if (loggedInUser) {
            setLoginInfo(loggedInUser);
            setLoggedIn(true);
        }
    }, [loggedIn]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await postUserInfo(loginInfo);
        saveUserToLocalStorage(response.data);
        setLoggedIn(true);
        setSubmitted(true);
        setUser(loginInfo);
    }

    // generic handle change
    const handleChange = (event) => {
        const { target: { name, value } } = event;
        setLoginInfo((loginInfo) => ({
            ...loginInfo,
            [name]: value, event: event,
        }));
    }

    const handleLogOut = () => {
        deleteUserFromLocalStorage();
        setSubmitted(false);
        setLoggedIn(false);
        setLoginInfo(userData);
        setUser(null);
    }

    const classes = useStyles();

    return (
        <main>
            <Container component="div" maxWidth='xs'>

                <div className={classes.paper}>

                    {loggedIn
                        ?
                        <div className={classes.loggedInHeader}>
                            <Typography component='h1' variant='h5'>
                                Logged In As
                        </Typography>
                            {/*TODO: this is needs to be styled different. currently it overflows  */}
                            <div className={classes.loggedInDiv}>
                                <label> Account Name: </label>
                                <label> {loginInfo.accountName} </label>
                                <label> League: </label>
                                <label> {loginInfo.league} </label>
                                <label> Realm: </label>
                                <label> {loginInfo.realm} </label>
                                <label> SessionId: </label>
                                <label> {loginInfo.sessionId} </label>
                            </div>
                            <Button
                                type='onClick'
                                fullWidth
                                variant='contained'
                                color='secondary'
                                className={classes.submit}
                                onClick={handleLogOut}
                            >
                                Sign out
                        </Button>
                        </div>
                        :
                        <div className={classes.loggedInHeader}>
                            <Typography component='h1' variant='h5'>
                                Logged In As
                        </Typography>
                            <form className={classes.form} onSubmit={handleSubmit}>
                                <TextField
                                    variant='outlined'
                                    margin='normal'
                                    required
                                    fullWidth
                                    id='accountName'
                                    label='Account Name'
                                    name='accountName'
                                    autoFocus
                                    value={loginInfo.accountName}
                                    onChange={handleChange}
                                />

                                {submitted && !loginInfo.accountName
                                    ? <span className={classes.validationError}>Please enter a account name</span>
                                    : null}

                                <TextField
                                    variant='outlined'
                                    margin='normal'
                                    required
                                    fullWidth
                                    id='league'
                                    label='League'
                                    name='league'
                                    value={loginInfo.league}
                                    onChange={handleChange}
                                />
                                {submitted && !loginInfo.league
                                    ? <span className={classes.validationError}>Please choose a league</span>
                                    : null}
                                <TextField
                                    variant='outlined'
                                    margin='normal'
                                    required
                                    fullWidth
                                    id='realm'
                                    label='Realm'
                                    name='realm'
                                    value={loginInfo.realm}
                                    onChange={handleChange}
                                />
                                {submitted && !loginInfo.realm
                                    ? <span className={classes.validationError}>Please choose a realm</span>
                                    : null}
                                <TextField
                                    variant='outlined'
                                    margin='normal'
                                    required
                                    fullWidth
                                    id='sessionId'
                                    label='Session ID'
                                    name='sessionId'
                                    type='password'
                                    value={loginInfo.sessionId}
                                    onChange={handleChange}
                                />
                                {submitted && !loginInfo.sessionId
                                    ? <span className={classes.validationError}>Please enter a sessionId</span>
                                    : null}
                                <Button
                                    type='submit'
                                    fullWidth
                                    variant='contained'
                                    color='primary'
                                    className={classes.submit}
                                >
                                    Sign In
                                </Button>
                            </form>
                        </div>
                    }
                </div>
            </Container >
        </main>
    );
}

export default Login;