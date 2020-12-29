import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Login.css";

export defualt function Login() {
    const [league, setLeague] = useState("");
    const [accountName, setAccountName] = useState("");
    const [realm, setRealm] = useState("");
    const [sessionId, setSessionId] = useState("");


    function validateForm() {
        return league.length > 0 &&
            accountName.length > 0 &&
            realm.length > 0 &&
            sessionId.length > 0;
    }

    function handleSubmit(event) {
        event.preventDefault();
    }

    return (
    <div className = "Login">
        <Form onSubmit={handleSubmit}>
            <Form.Group size = "lg" controlId="league">
                <Form.Label>League</Form.Label>
                <Form.Control
                    autoFous
                    type="league"
                    onChange={(e) => setLeague(e.target.value)}
                />
            </Form.Group>
            <Form.Group size = "lg" controlId="accountName">
                <Form.Label>Account Name</Form.Label>
                <Form.Control
                    autoFous
                    type="accountName"
                    onChange={(e) => setAccountName(e.target.value)}
                />
            </Form.Group>
            <Form.Group size = "lg" controlId="realm">
                <Form.Label>Realm</Form.Label>
                <Form.Control
                    autoFous
                    type="realm"
                    onChange={(e) => setRealm(e.target.value)}
                />
            </Form.Group>
            <Form.Group size = "lg" controlId="sessionId">
                <Form.Label>Session ID</Form.Label>
                <Form.Control
                    autoFous
                    type="league"
                    onChange={(e) => setSessionId(e.target.value)}
                />
            </Form.Group>
            <Button block size="lg" type="submit" disabled={!validateForm()}>
                Login
            </Button>
        </Form>
    </div>
    );
}