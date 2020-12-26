import React, { Component } from "react"
import { BrowserRouter as Router, Route } from "react-router-dom"


class SubscriptionMenu extends Component {

    constructor() {
        super()
    }



    render() {
        return (
            <Router>
                <Route>
                    <main className="subscription-main">
                        <h3>this worked</h3>
                    </main>
                </Route>
            </Router>
        )
    }


}

export default SubscriptionMenu