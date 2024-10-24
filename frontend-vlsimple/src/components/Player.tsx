import React from 'react';
import '../App.tsx';

const Player: React.FC = () => {
  return (
    <div className='Container'>
      <h2>Now Playing: some song in the future</h2>
      <div className='Controls'>
        <button className='Buttons'>Play</button>
        <button className='Buttons'>Pause</button>
        <button className='Buttons'>Stop</button>
      </div>
      <div className='Progress'>
        <span>00:00</span>
        <input type="range" min="0" max="100" />
        <span>03:45</span>
      </div>
    </div>
  );
};

export default Player;