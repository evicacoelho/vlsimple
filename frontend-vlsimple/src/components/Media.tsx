import React, { useEffect, useState } from 'react';
import { fetchMedia } from '../api/api.ts';
import MediaUploadModal from './MediaUploadModal.tsx'

interface Media {
  id: number;
  title: string;
  artist: string;
  locale: string;
}

const MediaList: React.FC = () => {
  const [media, setMedia] = useState<Media[]>([]);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  useEffect(() => {
    const getMedia = async () => {
      const data = await fetchMedia();
      setMedia(data);
    };
    getMedia();
  }, []);

  const OpenModal = () => {
    setIsModalOpen(true);
  }

  const CloseModal = () => {
    setIsModalOpen(false);
  };

  const handleUpload = (files: FileList) => {
    // will integrate with backend soon
  };

  return (
    <div>
      <h2>Media List</h2>
      <button onClick={() => OpenModal}>Add Media</button>
      <ul>
        {media.map((item) => (
          <li key={item.id}>
            {item.title} by {item.artist} - Path: {item.locale}
          </li>
        ))}
      </ul>
      <MediaUploadModal
      
        isOpen={isModalOpen}
        onClose={CloseModal}
        onUpload={handleUpload}
      />
    </div>
  );
};

export default MediaList;
