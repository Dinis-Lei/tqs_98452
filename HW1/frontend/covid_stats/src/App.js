
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
  const [nDays, setNDays] = useState(3);


  useEffect(() => {    
    let resp = fetchData(country + '?numberOfDays=' + nDays); // data is a promise object
    resp.then(function (result) {
        console.log(result);
        setData(result);
    });

}, [flg]);


  return (
    <div className="App">
      <h2>Covid Stats</h2>
      <Form className='col-6 mx-3'>
        <InputGroup className="mb-3">
          <Button itemID='search-btn' variant="outline-secondary" id="button-addon1" onClick={() => setFlg(!flg)}>
            Search
          </Button>
          <FormControl
            itemID='country-inpt'
            placeholder='Ex: usa'
            //value={country}
            onChange={(e) => {setCountry(e.target.value);}}
          />
          <input
            itemID='nDays-inpt'
            type="number"
            placeholder='3'
            className='mx-3 col-2'
            onChange={(e) => {setNDays(e.target.value);}}
          />
        </InputGroup>
      </Form>

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
              {data.map( (value, i) => {
                console.log(value);
                return <tr>     
                        <td itemID={'day' + i}>{value.day == null ? 'NA' : value.day}</td>
                        <td itemID={'newCases' + i}>{value.newCases == null ? 'NA' : value.newCases}</td>
                        <td itemID={'totalCases' + i}>{value.totalCases == null ? 'NA' : value.totalCases}</td>
                        <td itemID={'activeCases' + i}>{value.activeCases == null ? 'NA' : value.activeCases}</td>
                        <td itemID={'newDeaths' + i}>{value.newDeaths == null ? 'NA' : value.newDeaths}</td>
                        <td itemID={'totalDeaths' + i}>{value.totalDeaths == null ? 'NA' : value.totalDeaths}</td>
                        <td itemID={'totalTests' + i}>{value.totalTests == null ? 'NA' : value.totalTests}</td>
                      </tr>
              } )}                        
          </tbody>
        }
        
      </Table>
    </div>
  );
}

export default App;
