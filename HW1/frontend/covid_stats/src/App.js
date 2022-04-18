
import { useState, useEffect } from 'react';
import { Table, Col, Form, Button, InputGroup, FormControl } from 'react-bootstrap';
import './App.css';

export const fetchData = (str) => {
  return fetch("http://localhost:8080/api/" + str)
    .then((response) => response.json());
}


function App() {

  const [flg, setFlg] = useState(false);
  const [country, setCountry] = useState('');

  const [data, setData] = useState('');


  useEffect(() => {    
    let resp = fetchData(country); // data is a promise object
    resp.then(function (result) {
        setData(result);
    });

}, [flg]);


  return (
    <div className="App">
      <h2>Covid Stats</h2>
      <div className='col-4 mx-3'>
        <InputGroup className="mb-3">
          <Button variant="outline-secondary" id="button-addon1" onClick={() => setFlg(!flg)}>
            Search
          </Button>
          <FormControl
            placeholder='Ex: usa'
            //value={country}
            onChange={(e) => {setCountry(e.target.value);  console.log(country)}}
          />
        </InputGroup>
      </div>

      <Table striped bordered hover variant='dark'>
        <thead>
          <tr>
            <th>Date</th>
            <th>New Cases</th>
            <th>Total Cases</th>
            <th>Active Cases</th>
            <th>New Deaths</th>
            <th>Total Deaths</th>
            <th>Total Tests</th>
          </tr>
        </thead>
        {data != '' &&
          <tbody>
            <tr>
              {console.log(data)}
              <td>{data.day}</td>
              <td>{data.newCases}</td>
              <td>{data.totalCases}</td>
              <td>{data.activeCases}</td>
              <td>{data.newDeaths}</td>
              <td>{data.totalDeaths}</td>
              <td>{data.totalTests}</td>
            </tr>
            

          </tbody>
        }
        
      </Table>
    </div>
  );
}

export default App;
