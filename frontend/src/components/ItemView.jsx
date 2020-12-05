import React, { Component } from 'react';
import { Card } from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.min.css';

class ItemView extends Component {

    constructor(props)
    {
        super(props);
        this.state = {
            stashTabs: [],
            isLoaded: false
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/stash')
            .then(response => response.json())
            .then(data => this.setState({stashTabs: data, isLoaded: true}));
    }   

    render() {
        const cards = []
      /*
        for (const [index, item] of this.state.stashTabs.entries()) {
            console.log(item);
            listItems.push(
                <li key={index}>{item.id}</li>
            )
        }*/
        
        console.log(this.state.stashTabs);
        
        if (this.state.isLoaded) {

            for (const [index, stashTab] of this.state.stashTabs.entries()) {
                if (stashTab && stashTab.items.length > 0) {

                    var items = []
                    
                    for (const [index, item] of stashTab.items.entries()) {
                        items.push(
                            <Card key={index} style={{ width: '12rem', backgroundColor: '#999', borderColor: '#FFF'}}>
                                <Card.Img variant="top" src={item.icon}  style={{ width: '4rem', height: '4rem', margin: 'auto'}} />
                                <Card.Body>
                                    <Card.Title>{item.name}</Card.Title>
                                    <Card.Text style={{ 'font-size': '10px', color: '#FFF'}}>{item.descrText}</Card.Text>
                                </Card.Body>
                            </Card>
                        )
                    }

                    cards.push(
                        <div>
                            <hr style={{'border-top': '3px solid #bbb'}} />
                            <h1>{stashTab.stash}</h1>
                            {items}
                        </div>
                    )
 
                }
            }
        }
      
        return (
          <div style={{ display: 'inline-flex', 'flex-wrap': 'wrap'}}>
            {cards}
          </div>
        )
      }
  }
  
  export default ItemView;