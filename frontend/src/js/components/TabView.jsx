/* eslint-disable no-unused-vars */
import React from 'react';
import { Component } from 'react';
import { Card } from 'react-bootstrap';

class TabView extends Component {

    constructor(props)
    {
        super(props);
        this.state = {
            StashTab: [],
            isLoaded: false
        };
    }

    componentDidMount() {
        fetch('http://pathofexile.com/api/public-stash-tabs')
            .then(response => response.json())
            .then(data => this.setState({stashTabs: data, isLoaded: true}));
    }   

    render() {
        const cards = []
        if (this.state.isLoaded) {
            for (const [index, stashTab] of this.state.stashTabs.entries()) {
                if (stashTab && stashTab.items.length > 0) {
                    var items = []
                    for (const [index, item] of stashTab.items.entries()) {
                        items.push(
                            <Card key={index} style={{ width: '12rem', backgroundColor: '#ccc', borderColor: '#FFF'}}>
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
  
  export default TabView;